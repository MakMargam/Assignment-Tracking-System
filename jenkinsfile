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
                    withMaven(maven : 'mvn-3.8.5') {  
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
                docker.withRegistry('', 'DCR-personal') {  
                    ats_image.push()
                    // sh "docker push my-image:${env.BUILD_ID}"

                }
                }
            }
        }
    }
}
