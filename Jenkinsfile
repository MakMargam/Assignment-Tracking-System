// @Library('global-vars') _
pipeline {
    checkout scm
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

                def ats_image = docker.build("mak2497/ats-image:${env.BUILD_ID}")
                docker.withRegistery('','DCR-personal'){
                    ats_image.push()
                }
                }
            }
        }
    }
}
