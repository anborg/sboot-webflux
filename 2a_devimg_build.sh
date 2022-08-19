source .env.sh
#mvn package -DskipTests
#mvn jib:dockerBuild -Djib.dockerClient.executable=$(which podman) \
mvn compile jib:dockerBuild \
  -Djib.dockerClient.executable=$(which podman) \
  -Djib.from.image=$IMG_FROM \
  -Djib.container.user=1001:1001 \
  -Djib.to.image=$TAG_ORG

#
#mvn compile com.google.cloud.tools:jib-maven-plugin:2.8.0:build -Dimage=$IMG_LOCAL

#podman image tag $IMG_LOCAL $TAG_ORG  $TAG_GCP
#mvn jib:build
#podman images

