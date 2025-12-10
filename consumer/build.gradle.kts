dependencies {
    implementation(project(mapOf("path" to ":core")))
    implementation("org.springframework.boot:spring-boot-starter-web")
    //implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka")
    implementation("org.springdoc:springdoc-openapi-starter-common:2.8.14")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.14")

}
