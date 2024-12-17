# Use OpenJDK 17 as the base image
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Install necessary dependencies (curl, gnupg, wget, ca-certificates)
RUN apt-get update && apt-get install -y \
    curl \
    gnupg \
    wget \
    ca-certificates \
    libxss1 \
    libappindicator3-1 \
    libasound2 \
    && rm -rf /var/lib/apt/lists/*

# Install Google Chrome using the .deb package (bypassing repo installation)
RUN apt-get update && apt-get install -y chromium \
    && rm -rf /var/lib/apt/lists/*

# Install Maven
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz | tar xz -C /opt \
    && ln -s /opt/apache-maven-3.8.6/bin/mvn /usr/local/bin/mvn

# Set the environment variables for Java and Maven
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV MAVEN_HOME=/opt/apache-maven-3.8.6

# Add the application code
COPY . /app

# Install Allure command-line tool
RUN curl -o allure-2.19.0.tgz -fsSL https://github.com/allure-framework/allure2/releases/download/2.19.0/allure-2.19.0.tgz \
    && tar -zxvf allure-2.19.0.tgz \
    && mv allure-2.19.0 /opt/allure \
    && ln -s /opt/allure/bin/allure /usr/local/bin/allure

# Set the default command to run Maven tests
CMD ["mvn", "clean", "test"]
