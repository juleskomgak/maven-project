pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                     echo 'Now Archiving ...'
                     archiveArtifacts artifacts: '**/target/*.war'
                }  

            }
        }
        stage(' Deploy To Staging ')  {
            steps {
                build job: 'deploy-to-staging'
            }

        }
        stage(' Deploy To Production') {
            steps {
                timeout(time:5, unit:'DAYS') {
                    input message: 'Approuve Production Deployment? '
                }
                build job: 'deploy-to-prod'

            }

            post {
                success {
                     echo 'Deploy To prod successfull'
                }

                failure {
                     echo 'Deploy To prod fail'
                }  

            }


        }
       
    }
}