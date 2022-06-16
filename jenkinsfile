// @Library('global-vars') _
pipeline {
    
    agent none;

    stages {
        stage('Maven Build') {
            
            agent any;
            tools {
                maven "MAVEN"
            }

            steps {
                
                script{
                    
                // git 'https://github.com/MakMargam/Assignment-Tracking-System.git'

                sh "mvn clean package"
                }
            }
        }
        stage('SonarQube analysis') { 
            steps{
                withSonarQubeEnv(credentialsId: 'sonar-scan-id', installationName: 'sonar-scan') { // You can override the credential to be used
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
                }
            }
        }
        stage('Docker Build'){
            agent any 
            steps{
                script{

                def ats_image = docker.build("mak2497/ats-image:0.0.1")
                docker.withRegistry('', 'DCR-personal') {  
                    ats_image.push()
                    // sh "docker push my-image:${env.BUILD_ID}"

                }
                }
            }
        }
    }
}
