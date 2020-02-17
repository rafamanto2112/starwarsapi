# README #

### Descrição ###

* Repositório responsável por manter a aplicação StarWars API
* Versão: 1.0.0-RELEASE


### Configurações ###

* Para rodar a aplicaçao, é necessário possuir o docker instalado na máquina. As instruções podem ser obtidas através do link: https://docs.docker.com/install/
* Tendo o projeto baixado do repositório, entrar no diretório raiz do projeto e executar o seguinte comando: docker-compose up


* Usando os serviços:

* Os serviços podem ser usados a partir da execução do container seguindo a url: http://localhost:8095/star-wars-api/planet/v1

* Lista de serviços:
	- listAllSaved (Lista todos os planetas salvos no banco de dados);
	- save (Insere ou atualiza um planeta a partir do ID);
	- remove (Remove um planeta a partir do ID);
	- findByName (Busca um planeta pelo nome);
	- findById (Busca um planeta pelo ID);
	- listAll (Lista todos os planetas a partir da API: https://swapi.co/

* Os contratos podem ser obtidos pela documentação no link: http://localhost:8095/star-wars-api/swagger-ui.html
