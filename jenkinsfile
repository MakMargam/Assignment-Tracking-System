@Library('global-vars') _
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
                    
                git 'https://github.com/MakMargam/Assignment-Tracking-System.git'

                sh "mvn clean package"
                }
            }
        }
        stage('Docker Build'){
            agent any 
            steps{
                script{

                docker.withRegistery('https://index.docker.io/v1/','DCR-personal'){
                    def ats_image = docker.build("mak2497/ats-image:${env.BUILD_ID}")
                    ats_image.push()
                }
                }
            }
        }
    }
}
