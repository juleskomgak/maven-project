pipeline {
    agent any
        parameters {           
            string(name: 'tomcat_dev', defaultValue: '-i /usr/local/Cellar/jenkins/2.95/tomcat-demo.pem **/target/*.war ec2-user@18.218.159.8:/var/lib/tomcat7/webapps', description: 'tomcat server for staging')
            string(name: 'tomcat_prod', defaultValue: '-i /usr/local/Cellar/jenkins/2.95/tomcat-demo.pem **/target/*.war ec2-user@18.217.102.171:/var/lib/tomcat7/webapps', description: 'tomcat server for production' )
            string(name: 'compy_cmd', defaultValue: 'scp', description: 'command to copy file to webapps ')
            
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
            
            steps {
                parallel (
                    "Deploy To Staging": {
                    
                            sh "${params.compy_cmd} ${params.tomcat_dev}"
                        
                    },

                    "Deploy To Production": {
                        
                            sh "${params.compy_cmd} ${params.tomcat_prod}"
                        
                    } )
               }
            }
        }

    



}