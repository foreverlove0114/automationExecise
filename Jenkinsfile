pipeline {
    agent any

    tools {
        maven 'Maven-3.9'  // Jenkins 全局配置的名称
        jdk 'JDK-21'       // Jenkins 全局配置的名称
    }

    environment {
        // 从 Jenkins 凭证注入
        TEST_PASSWORD = credentials('test-password')
        TEST_EMAIL_VALID = credentials('test-email-valid')

        // 报告目录
        REPORT_DIR = 'reports'
        SCREENSHOT_DIR = 'screenshots'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm  // 自动从 GitHub 拉取代码
            }
        }

        stage('Build & Test') {
            steps {
                sh """
                    mvn clean test \
                        -Dsurefire.suiteXmlFiles=testNG.xml \
                        -Dtest.env=jenkins \
                        -Dbrowser=chrome \
                        -Dheadless=true
                """
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: "${REPORT_DIR}",
                    reportFiles: '*.html',
                    reportName: 'Test Automation Report'
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: "${REPORT_DIR}/**/*.html, ${SCREENSHOT_DIR}/**/*.png",
                           allowEmptyArchive: true
            testNG(reportFilenamePattern: 'target/surefire-reports/testng-results.xml')
        }
    }
}