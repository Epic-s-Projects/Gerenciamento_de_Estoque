# Use uma imagem base do OpenJDK
FROM openjdk:11-jre-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR da sua aplicação para o contêiner
COPY target/gerenciamento-3.9.7.jar /app/gerenciamento-3.9.7.jar

# Comando para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "gerenciamento-3.9.7.jar"]