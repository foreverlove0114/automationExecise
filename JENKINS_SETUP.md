# Jenkins 配置指南

## 一、jenkins-setup.groovy 使用方法

### 方式 1: 通过 Jenkins Script Console 运行（推荐）

1. **启动 Jenkins**
2. **访问 Script Console**: 
   - URL: `http://your-jenkins-url/script`
   - 路径：Manage Jenkins > Script Console

3. **运行脚本**:
   ```groovy
   // 复制 jenkins-setup.groovy 的全部内容并粘贴到 Script Console
   // 点击 Run 按钮执行
   ```

4. **查看输出**: 控制台会显示配置进度和下一步操作提示

### 方式 2: 作为 Jenkins 启动脚本

1. 将 `jenkins-setup.groovy` 放到 Jenkins 主目录的 `init.groovy.d/` 文件夹
   ```bash
   cp jenkins-setup.groovy $JENKINS_HOME/init.groovy.d/
   ```

2. 重启 Jenkins
   ```bash
   systemctl restart jenkins
   ```

3. 查看日志确认配置成功
   ```bash
   tail -f /var/log/jenkins/jenkins.log
   ```

---

## 二、手动配置步骤（如果不使用脚本）

### 1. 安装必要插件

访问：**Manage Jenkins > Manage Plugins > Available**

搜索并安装以下插件：
- ✅ TestNG Results Plugin
- ✅ HTML Publisher Plugin  
- ✅ Git Plugin
- ✅ Maven Integration Plugin
- ✅ Pipeline Plugin

安装后重启 Jenkins

### 2. 配置全局工具

访问：**Manage Jenkins > Global Tool Configuration**

#### 配置 JDK:
```
Name: JDK-21
JAVA_HOME: /usr/lib/jvm/java-21-openjdk-amd64
# 或取消勾选 "Install automatically" 并指定 Path
```

#### 配置 Maven:
```
Name: Maven-3.9
MAVEN_HOME: /opt/maven/apache-maven-3.9.0
# 或勾选 "Install automatically" 让 Jenkins 自动安装
```

### 3. 添加凭证

访问：**Manage Jenkins > Manage Credentials > System > Global credentials (unrestricted)**

点击 **"Add Credentials"** 添加以下凭证：

#### 凭证 1: test-password
```
Kind: Secret text
Secret: [你的测试密码]
ID: test-password
Description: Test account password for automation
```

#### 凭证 2: test-email-valid
```
Kind: Secret text
Secret: testuser@example.com
ID: test-email-valid
Description: Valid test email address
```

### 4. 创建 Pipeline Job

1. **点击 "New Item"**

2. **输入名称**: `AutomationExecise`

3. **选择类型**: `Pipeline`

4. **配置 Pipeline**:
   ```
   General:
     ✓ Discard old builds
       Max # of builds to keep: 10
   
   Pipeline:
     Definition: Pipeline script from SCM
     SCM: Git
     Repository URL: https://github.com/yourusername/AutomationExecise.git
     Branch Specifier: */main
     Script Path: Jenkinsfile
   ```

5. **保存并运行**: 点击 "Build Now"

---

## 三、pom.xml 配置说明

### 已修复的问题:
✅ 删除了重复的 `<properties>` 标签
✅ 清理了 Surefire 插件配置
✅ 保留了必要的属性定义

### 关键属性:
```xml
<properties>
    <suiteXmlFile>testNG.xml</suiteXmlFile>
    <browser>chrome</browser>
    <headless>false</headless>
    <test.env>local</test.env>
    <aspectj.version>1.9.21</aspectj.version>
</properties>
```

### 本地运行测试:
```bash
# 默认配置
mvn clean test

# 指定浏览器
mvn clean test -Dbrowser=firefox

# 无头模式（CI/CD 环境）
mvn clean test -Dheadless=true

# 指定测试套件
mvn clean test -DsuiteXmlFile=crossBrowserTests.xml
```

---

## 四、环境变量配置（可选）

### 在 Jenkins Pipeline 中使用环境变量:

```groovy
environment {
    TEST_PASSWORD = credentials('test-password')
    TEST_EMAIL = credentials('test-email-valid')
}
```

### 在本地开发环境:

**Linux/Mac:**
```bash
export TEST_PASSWORD=test123
export TEST_EMAIL=test@example.com
mvn clean test
```

**Windows PowerShell:**
```powershell
$env:TEST_PASSWORD="test123"
$env:TEST_EMAIL="test@example.com"
mvn clean test
```

---

## 五、验证配置

### 1. 测试 Jenkins 配置
```bash
# 在 Jenkins 上运行
mvn clean test -Dtest.env=jenkins -Dheadless=true
```

### 2. 检查报告生成
- TestNG 报告应该在：`target/surefire-reports/`
- HTML 报告应该在：`reports/`
- 截图应该在：`screenshots/`

### 3. 验证凭证注入
在 Pipeline 中添加测试步骤：
```groovy
stage('Test Credentials') {
    steps {
        sh 'echo "Password is set: ${TEST_PASSWORD != null}"'
    }
}
```

---

## 六、故障排查

### 问题 1: Maven 找不到
```
解决方案:
1. 检查 Global Tool Configuration 中 Maven 路径
2. 或在 Jenkinsfile 中勾选 "Install automatically"
```

### 问题 2: 凭证未找到
```
错误：credentials('test-password') not found
解决方案:
1. 确认凭证 ID 完全匹配
2. 检查凭证是否在 Global credentials 中
3. 确保 Pipeline 有权限访问凭证
```

### 问题 3: 报告未生成
```
解决方案:
1. 检查 pom.xml 中 surefire-plugin 配置
2. 确认 reports/ 目录存在
3. 检查文件权限
```

### 问题 4: POM 解析错误
```
错误：Non-parseable POM - Duplicated tag
解决方案:
✅ 已修复！删除了重复的 <properties> 标签
重新 sync Maven 项目即可
```

---

## 七、最佳实践

1. **不要提交敏感信息到 Git**
   - 使用 Jenkins Credentials
   - 使用环境变量
   - 使用 .gitignore 排除本地配置文件

2. **保持构建可重复**
   - 固定 Maven 和 JDK 版本
   - 使用 Docker 容器化测试环境

3. **定期清理**
   - 启用 "Discard old builds"
   - 定期清理工作空间

4. **监控资源使用**
   - 设置 MAVEN_OPTS: `-Xmx1024m`
   - 限制并发构建数

---

## 八、后续优化建议

1. **集成 Docker**
   ```groovy
   agent {
       docker {
           image 'maven:3.9-openjdk-21'
           args '-v /var/run/docker.sock:/var/run/docker.sock'
       }
   }
   ```

2. **并行执行测试**
   ```xml
   <configuration>
       <parallel>methods</parallel>
       <threadCount>4</threadCount>
   </configuration>
   ```

3. **添加通知**
   ```groovy
   post {
       failure {
           mail to: 'team@example.com',
                subject: "Failed: ${currentBuild.fullDisplayName}",
                body: "Check: ${env.BUILD_URL}"
       }
   }
   ```

---

需要更多帮助？查看：
- Jenkins 官方文档：https://www.jenkins.io/doc/
- Maven Surefire Plugin: https://maven.apache.org/surefire/maven-surefire-plugin/
- TestNG 文档：https://testng.org/doc/
