// jenkins-setup.groovy - Jenkins 初始化配置脚本
// 用于在 Jenkins 启动时自动配置必要的插件和全局配置

import jenkins.model.*
import hudson.model.*
import hudson.tasks.*
import hudson.tools.*
import java.nio.file.*

def instance = Jenkins.getInstance()

// 1. 配置全局工具
println "=== Configuring Global Tools ==="

// Maven 配置
def mavenInstallations = [
    new hudson.tasks.Maven.MavenInstallation(
        "Maven-3.9",
        "/opt/maven/apache-maven-3.9.0",
        Collections.emptyList()
    )
]
instance.getDescriptorByType(hudson.tasks.Maven.DescriptorImpl.class).setInstallations(mavenInstallations as hudson.tasks.Maven.MavenInstallation[])

// JDK 配置
def jdkInstallations = [
    new hudson.model.JDK(
        "JDK-21",
        "/usr/lib/jvm/java-21-openjdk-amd64"
    )
]
instance.getDescriptorByType(hudson.model.JDK.DescriptorImpl.class).setInstallations(jdkInstallations as JDK[])

// 2. 配置系统环境变量
println "=== Configuring System Environment ==="
def globalNodeProperties = new hudson.slaves.EnvironmentVariablesNodeProperty()
def envVars = globalNodeProperties.getEnvVars()
envVars.put("MAVEN_OPTS", "-Xmx1024m")
envVars.put("GIT_SSL_NO_VERIFY", "true")

// 3. 配置 TestNG 报告插件（如果已安装）
println "=== Configuring TestNG Plugin ==="
def testngDescriptor = instance.getDescriptor("org.jvnet.hudson.plugins.testng.TestNGProjectConfig")
if (testngDescriptor != null) {
    println "TestNG plugin found and configured"
} else {
    println "TestNG plugin not found. Please install from: Manage Jenkins > Manage Plugins"
}

// 4. 配置 HTML 报告插件
println "=== Configuring HTML Publisher Plugin ==="
def htmlPublisher = instance.getDescriptor("htmlpublisher.HtmlPublisherTarget")
if (htmlPublisher != null) {
    println "HTML Publisher plugin found"
} else {
    println "HTML Publisher plugin not found. Please install it."
}

// 5. 配置 Git 凭证（示例）
println "=== Setting up Git Credentials (Example) ==="
// 注意：实际凭证应该通过 Jenkins UI 手动添加
// 路径：Manage Jenkins > Manage Credentials > System > Global credentials

// 6. 配置邮件通知（可选）
println "=== Configuring Email Notification ==="
def mailer = instance.getDescriptor("hudson.tasks.Mailer")
if (mailer != null) {
    // mailer.setSmtpServer("smtp.company.com")
    // mailer.setDefaultSuffix("@company.com")
    println "Mailer configured"
}

// 7. 保存配置
println "=== Saving Configuration ==="
instance.save()
println "✅ Jenkins setup completed successfully!"

// 8. 输出后续步骤
println """
===========================================
Next Steps:
1. Install required plugins:
   - TestNG Results Plugin
   - HTML Publisher Plugin
   - Git Plugin
   - Maven Integration Plugin

2. Add credentials via Jenkins UI:
   - Navigate to: Manage Jenkins > Manage Credentials
   - Add these credentials:
     * test-password (Secret text)
     * test-email-valid (Secret text)
   
3. Create a new Pipeline job:
   - Click 'New Item'
   - Enter job name (e.g., 'AutomationExecise')
   - Select 'Pipeline'
   - In 'Pipeline Definition', select 'Pipeline script from SCM'
   - Choose Git and point to your repository
   - Set 'Script Path' to: Jenkinsfile

4. Run the pipeline!
===========================================
"""
