# Desafio
Criar uma aplicação java web onde:
Como usuário da aplicação posso:
- Cadastrar os clientes da minha empresa
- Cadastrar os serviços  que minha empresa oferece
- Registrar que meu cliente contratou um serviço da minha empresa onde possa definir a data de início e de fim do serviço
- Visualizar quais serviços meu cliente tem contratado e quantos dias faltam para atingir a data final do serviço. 
Também gostaria de visualizar o valor  dos  serviços contratados por um cliente, onde:
- Clientes do tipo Ouro têm 10% de desconto
- Clientes do tipo Prata que têm 5% de desconto
- Se o pagamento for até 10  dias antes da data final, dar mais 5% de desconto no cálculo do valor (considerando a data atual)
Além disso gostaria de disponibilizar um serviço REST que retorne um JSON contendo os dados de todos os  serviços da minha empresa. Esse serviço poderá ser consumido por uma outra aplicação.
Requisitos técnicos:
- Backend em Java
- Deverá utilizar uma framework ORM para persistência dos dados
- Banco pode ser o MySQL
- Front-end pode ser feito da forma que quiser

Criar a base de dados da aplicação `create database servicos;`

O arquivo `aplicattion.properties` que está na pasta `servicos\src\main\resources` contém user e password do MySQL Server para acesso da aplicação, também está a porta que será usada pelo Tomcat.

Para acesso aos **serviços** em formato JSON acesse `localhost:porta/rest/servicos/`

Para mais detalhes sobre o que foi usado no projeto consulte o arquivo `pom.xml`
