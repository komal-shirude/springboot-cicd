pipeline {

    agent any

    tools {
        maven 'maven'
        jdk 'java21'
    }

    environment {

        IMAGE_NAME = "komalshirode/springboot-cicd"
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {

        stage('Git Checkout') {

            steps {

                git branch: 'main',
                url: 'https://github.com/komal-shirude/springboot-cicd.git'
            }
        }

        stage('Build Maven') {

            steps {
                bat 'mvn clean package'
            }
        }

        stage('Build Docker Image') {

            steps {
                bat 'docker build -t %IMAGE_NAME%:%IMAGE_TAG% .'
            }
        }

        stage('Docker Login') {

            steps {

                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                }
            }
        }

        stage('Push Docker Image') {

            steps {
                bat 'docker push %IMAGE_NAME%:%IMAGE_TAG%'
            }
        }

       stage('Set Kubeconfig') {
    steps {
        bat '''
        set KUBECONFIG=C:\\ProgramData\\Jenkins\\.kube\\config
        kubectl get nodes
        '''
    }
}

        stage('Deploy Kubernetes') {
            steps {
                bat '''
                kubectl apply -f k8s/
                kubectl set image deployment/springboot-cicd springboot-cicd=%IMAGE_NAME%:%IMAGE_TAG%
                '''
            }
        }
    }
    post {

        success {
            echo 'CI/CD Pipeline Success!'
        }

        failure {
            echo 'Pipeline Failed!'
        }
    }
}