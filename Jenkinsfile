// @Library('global-vars') _
pipeline {
    
    agent none;

    stages {
        // stage('checkout'){
        //     steps{
        //         checkout SCM;
        //     }
        // }
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
        // stage('SonarQube analysis') { 
        //     agent any
        //     // node {
        //     //     stage('SCM') {
        //     //         git 'https://github.com/foo/bar.git'
        //     //     }
        //     //     stage('SonarQube analysis') {
        //     //         withSonarQubeEnv(credentialsId: 'f225455e-ea59-40fa-8af7-08176e86507a', installationName: 'My SonarQube Server') { // You can override the credential to be used
        //     //         sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
        //     //         }
        //     //     }
        //     // }
        //     steps{
        //         withSonarQubeEnv(credentialsId: 'sonar-scan', installationName: 'sonar-scan') { // You can override the credential to be used
        //             sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
        //         }
        //     }
        // }
        stage('Dependency check'){
            agent any
            steps {  
                    withMaven(maven : 'MAVEN') {  
                    sh 'mvn dependency-check:check'  
                }  
            
                dependencyCheckPublisher pattern: 'target/dependency-check-report.xml'  
            } 
        }
        stage('Docker Build'){
            agent any 
            steps{
                script{

                def ats_image = docker.build("mak2497/ats-image:0.0.1")
                // curl https://registry-1.docker.io/v2/ && echo Works
                docker.withRegistry('', 'DCR-token') {  
                    ats_image.push()
                    // sh "docker push my-image:${env.BUILD_ID}"

                }
                }
            }
        }
    }
}
