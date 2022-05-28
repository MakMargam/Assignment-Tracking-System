@Library('global-vars') _
pipeline {
    agent none;

    stages {
        stage('Maven Build') {
            agent any;
            tools {
                // Install the Maven version configured as "M3" and add it to the path.
                maven "MAVEN"
            }

            steps {
                script{
                    
                // Get some code from a GitHub repository
                git 'https://github.com/MakMargam/Assignment-Tracking-System.git'

                sh "mvn clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
                }
            }
        }
        stage('Docker Build'){
            steps{
                ats_image = docker.build("mak2497/ats-image:${env.BUILD_ID}")
                docker.withRegistery('','DCR-personal'){
                    ats_image.push()
                }
            }
        }
        
    }
}
