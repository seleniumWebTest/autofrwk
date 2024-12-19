pipeline {
    agent any

    tools {
        maven 'Maven 3.8.3'  // Make sure this matches your Maven version
        jdk 'JDK 17'         // Your JDK version
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/seleniumWebTest/autofrwk.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Run Maven build and tests
                    sh 'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run tests using TestNG
                    sh 'mvn test'
                }
            }
        }

        stage('Allure Report') {
            steps {
                script {
                    // Generate Allure Report
                    sh 'mvn allure:serve'
                }
            }
        }
    }

    post {
        always {
            junit '**/target/test-classes/testng-*.xml' // Collect TestNG reports
        }
    }
}
