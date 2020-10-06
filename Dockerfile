FROM openjdk:11
WORKDIR /app/
COPY ./* ./
RUN javac main.java