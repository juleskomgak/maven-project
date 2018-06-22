pipeline {
    agent any
        parameters {
            string(name: 'tomcat_dev', defaultValue: '18.218.159.8', description: 'tomcat server for staging')
            string(name: 'tomcat_prod', defaultValue: '18.217.102.171', description: 'tomcat server for production' )
        }

        triggers {
            pollSCM('* * * * *')
        }
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
        stage('Deployments') {
            parralel{
                stage('Deploy To Staging'){
                    steps {
                        sh "scp -i /usr/local/Cellar/jenkins/2.95/tomcat-demo.pem **/target/*.war"
                    }
                }

                stage('Deploy To Production'){
                    steps {
                        sh "scp -i /usr/local/Cellar/jenkins/2.95/tomcat-demo.pem **/target/*.war"
                    }
                }

            }
        }

    }



}