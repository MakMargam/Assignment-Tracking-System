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
        // stage('Docker Build'){
        //     agent any 
        //     steps{
        //         script{

        //         docker.withRegistery('https://index.docker.io/v1/','DCR-personal'){
        //             def ats_image = docker.build("mak2497/ats-image:${env.BUILD_ID}")
        //             ats_image.push()
        //         }
        //         }
        //     }
        // }
        stage('Docker Build'){
            agent any
            steps{
                sh 'docker build -t mak2497/ats-image:${env.BUILD_ID} .'
            }
        }
        
    }
}
