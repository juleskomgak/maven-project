pipeline {
    agent any
        parameters {           
            string(name: 'tomcat_dev', defaultValue: '18.218.159.8', description: 'tomcat server for staging')
            string(name: 'tomcat_prod', defaultValue: '18.217.102.171', description: 'tomcat server for production' )
            string(name: 'compy_cmd', defaultValue: 'scp', description: 'command to copy file to webapps ')
            string(name: 'webapps_path_staging', defaultValue: '/var/lib/tomcat7/webapps', description: 'tomcat server path for staging')
            string(name: 'webapps_path_prod', defaultValue: '/var/lib/tomcat7/webapps', description: 'tomcat server path for prod')
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
                    
                            sh "${params.compy_cmd} -i /usr/local/Cellar/jenkins/2.95/tomcat-demo.pem **/target/*.war  ec2-user@${params.tomcat_dev}:${params.webapps_path_staging}"
                        
                    },

                    "Deploy To Production": {
                        
                            sh "${params.compy_cmd} -i /usr/local/Cellar/jenkins/2.95/tomcat-demo.pem **/target/*.war ec2-user@${params.tomcat_prod}:${params.webapps_path_prod}"
                        
                    } )
               }
            }
        }

    



}