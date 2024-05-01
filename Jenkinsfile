pipeline{
    agent any
    tools{
        maven "maven"

    }
    stages{
        stage("Build JAR File"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Irictm/lab1TINGESO.git']])
                bat "set IVA=0.19"
                bat "mvn clean package"
            }
        }
        stage("Unit Tests"){
            steps{
                bat "mvn test"
            }
        }
        stage("Build and Push Docker Image"){
            steps{
                script{
                    withDockerRegistry(credentialsId: 'docker-credentials'){
                        bat "docker build -t irictm/lab1-tingeso-backend:latest ."
                        bat "docker push irictm/lab1-tingeso-backend:latest"
                    }
               }
            }
        }
    }
}