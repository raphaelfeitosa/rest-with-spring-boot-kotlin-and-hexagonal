## SonarQube
- Requerimentos para subir imagem do docker
  - Step 1: No Linux, você pode definir os valores recomendados para a sessão atual executando os seguintes comandos como root no host:
  ```
   sysctl -w vm.max_map_count=524288
   sysctl -w fs.file-max=131072
   ulimit -n 131072
   ulimit -u 8192
  ```
- Step 2: Subindo imagem do sonar
  ````
  - docker-compose up --build
  ````

- Step 3: Executando build:
  ```
  - Windows - cmd: ./mvnw clean test sonar:sonar -DskipITs -DexcludedGroups="integration"
  - Linux/Windows gitbash: mvn clean test sonar:sonar -DskipITs -DexcludedGroups="integration"
  ```