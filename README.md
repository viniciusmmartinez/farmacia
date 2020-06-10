# Teste Apontador Farmacia

Está aplicação é composta por um inclusão e consulta de farmácia. Onde foram utilizadas as seguintes tecnologias:
Frontend: 
 - Angular 8
 - Boostrap
 - Primefaces
 
 Backend:
 - Java 11
 - Spring boot
 - Database MySql
 - Lombook
 - Maven
 - Hibernate
 - JPA
 - Spring Data

# Arquivos

Frontend: Toda a parte do frontend está localizada no seguinte diretório: farmacia/front-end

Backend: Toda a parte do backend está localizada no seguinte diretório:
farmacia/ProjetoFarmacia
	
## Roteiro instalação
| FRONTEND
>Ferramentas necessárias:
- Node 8.9.3 (arquivo node-v8.9.3-x64.msi)
- Visual Studio Code 1.18.1 (arquivo VSCodeSetup-x64-1.18.1.exe)

> Instalar dependências do Node.js globais:
- Abrir uma janela do prompt como Administrador
- Executar os seguintes comandos:
  > npm install --global typescript
  > npm install --global @angular/cli
  * Confirme que os comandos concluiram com sucesso
- Fechar esta janela

> Passos finais no Visual Studio Code (resumido de VSC)
- Abrir o VSC
- No VSC, abrir a pasta \farmacia\frontend-farmacia
- No VSC, abrir o terminal integrado ( menu View \ Integrated
  Terminal, ou as teclas de atalho Ctrl+' )
- Executar no terminal integrado:

      > ng serve

- Testar se o Front-end subiu corretamente através da
  URL: http://localhost:4200

|BACKEND
>Ferramentas necessárias:
- Maven 3.6.2
- JDK 11
- Docker
- Eclipse ou Intelij
>Gerar build do projeto
-Dentro da pasta farmacia\ProjetoFarmacia executar o comando: mvn clean package

>Subir aplicação
>mvnw spring-boot:run
-Dentro da pasta farmacia\ProjetoFarmacia executar o comando: Testar se o Backend subiu atráves da 
URL http://localhost:8080/apontador/v1/farmacia

## Docker

>Docker Compose:
-O docker compose se encontra na raiz do projeto, ou seja, /farmacia. Este é responsável por subir a aplicação frontend, database mySql e backend, sem a necessidade de configurar o ambiente. As portas expostas são para o frontend https://localhost:4200 e backend https://localhost:8080
-Executar: Para subir o contair com as imagens, basta digitar no prompt de comando dentro da pasta farmacia:
	**`docker-compose up -d --build`**
>Docker File (é possível gerar as imagens para cada item conforme seu docker file)
-frontend: farmacia/frontend-farmacia
-backend: ProjetoFarmacia
-data base: ProjetoFarmacia 

## Subir Aplicação sem ambiente
-Executar: Para subir o contair com as imagens, basta digitar no prompt de comando dentro da pasta farmacia:
	**`docker-compose up -d --build`**
