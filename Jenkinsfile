pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
        DOCKER_IMAGE = 'selenium-cucumber-test'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Checkout the repository code
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the app Docker image using the Dockerfile
                    sh "docker-compose -f ${DOCKER_COMPOSE_FILE} build app"
                }
            }
        }

        stage('Start Services') {
            steps {
                script {
                    // Start the Selenium service and the app container
                    sh "docker-compose -f ${DOCKER_COMPOSE_FILE} up -d selenium app"
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Run Maven tests in the app container (which depends on Selenium)
                    sh "docker-compose -f ${DOCKER_COMPOSE_FILE} exec app mvn clean test"
                }
            }
        }

        // stage('Generate Allure Report') {
        //     steps {
        //         script {
        //             // Generate Allure report (assuming Maven generates Allure results in target/allure-results)
        //             sh "docker-compose -f ${DOCKER_COMPOSE_FILE} exec app allure generate target/allure-results --clean -o target/allure-report"
        //         }
        //     }
        // }

        // stage('Publish Allure Report') {
        //     steps {
        //         script {
        //             // Publish Allure report (can be customized based on how you want to display it)
        //             allure([
        //                 results: [[path: 'target/allure-results']],
        //                 reportBuildPolicy: 'ALWAYS'
        //             ])
        //         }
        //     }
        // }

        stage('Stop Services') {
            steps {
                script {
                    // Stop and remove the containers after tests are done
                    sh "docker-compose -f ${DOCKER_COMPOSE_FILE} down"
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker images and containers to free up space
            sh "docker system prune -f"
        }

        success {
            echo 'Build and Tests Succeeded!'
        }

        failure {
            echo 'Build or Tests Failed.'
        }
    }
}
