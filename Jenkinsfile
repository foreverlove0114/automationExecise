pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-21'
    }

    environment {
        TEST_PASSWORD = credentials('test-password')
        TEST_EMAIL_VALID = credentials('test-email-valid')
        MAVEN_OPTS = '-Xmx1024m'
        REPORT_DIR = 'reports'
        SCREENSHOT_DIR = 'screenshots'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat '''
                    @echo off
                    echo ====================================
                    echo Running Maven Tests on Windows
                    echo ====================================
                    call mvn --version
                    call java -version
                    call mvn clean test -Dsurefire.suiteXmlFiles=testNG.xml -Dtest.env=jenkins -Dbrowser=chrome -Dheadless=true
                    if %ERRORLEVEL% NEQ 0 (
                        echo Tests failed with error code: %ERRORLEVEL%
                        exit /b %ERRORLEVEL%
                    )
                '''
            }
        }

        stage('Publish Reports') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: '*.html',
                    reportName: 'Test Automation Report'
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'reports/**/*.html, screenshots/**/*.png',
                           allowEmptyArchive: true,
                           fingerprint: true
            
            junit allowEmptyResults: true,
                  testResults: 'target/surefire-reports/TEST-*.xml'
        }

        success {
            echo 'All tests passed!'
        }

        failure {
            echo 'Tests failed. Check console output for details.'
            archiveArtifacts artifacts: 'reports/**/*.html, screenshots/**/*.png',
                           allowEmptyArchive: true
        }

        unstable {
            echo 'Tests are unstable.'
        }
    }
}