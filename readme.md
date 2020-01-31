# Api Media enem.

Api feita para calcular a média do ENEM em todas as **faculdades** que possui aquele **curso** e mostrar a última nota de corte. Foi desenvolvido para resolver uma dor do usuário, que ao acessar o site do SISU ele deve entrar em faculdade por faculdade para ver qual a nota de corte desse curso naquela faculdade e campus, nosso site disponibiliza a lista de todas as faculdades que possuem o curso e qual a nota atual. 


![Tela de consulta](https://github.com/ramoncgusmao/notasisuenem/blob/master/imagens/foto%20site%20do%20enem.PNG)
##### pagina inicial do site para o usuário colocar sua nota e pesquisar o curso.
 &nbsp;
![Lista faculdades web](https://github.com/ramoncgusmao/notasisuenem/blob/master/imagens/lista%20de%20notas.PNG)
##### Lista com todas as faculdades, apresentada em formato de lista ordenada por onde o aluno tem mais chances de ser aprovado
![Lista de faculdades mobile](https://github.com/ramoncgusmao/notasisuenem/blob/master/imagens/faculdade%20lista%20mobile.PNG)
##### Lista com todos as faculdades, apresentada em formato de cards devido às dimensões da tela estarem em um tamanho menor.


O sistema está no ar e você pode acessar nessa URL: <http://aprovavest.com.br/>


Dividimos o site em FrontEnd e BackEnd. Onde o Backend foi feito por mim e o frontend foi desenvolvido pelo André Marcelo e você pode ver o código na URL: <https://github.com/AndreMRego/compare-notes>


Como funciona a aplicação. Cadastramos os dados do SISU como: estado, faculdade, campus, curso faculdade, tipo de vaga, pesos da nota de corte, quantidade de vagas, nota mínima em cada prova, nota de corte do dia em cada tipo vaga do curso faculdade. Após ter esses dados todos na base que retiramos do SISU que ele liberou um excel de carga e você consegue acessar a Api deles. Caso queria saber todos os nossos Endpoint pode acessar o link:  <https://notadecortesisu.herokuapp.com/swagger-ui.html#/> que aparecerá o nosso arquivo de Swagger com todos os Endpoins e que dado estamos passando. Estamos utilizando Spring Security então só é permitido fazer alterações logado.


![Swagger](https://github.com/ramoncgusmao/notasisuenem/blob/master/imagens/swagger%20api.PNG)


O site foi publicado dia 17/01/2020 e tivemos poucos acessos, pois nesse momento o usuário só conseguia ver a sua nota na faculdade. Mas a partir do dia 23/01/2020 tivemos um boom de acessos, pois estávamos com as notas de corte atualizadas e é fácil de visualizar. No dia 23/01/2020 tivemos 2222 acessos.
![Resultados do site](https://github.com/ramoncgusmao/notasisuenem/blob/master/imagens/resultados%20do%20site.PNG)

Criamos esse sistema para ajudar os seguidores do <https://www.instagram.com/aprovavest/> que é um Instagram que eu administro, que todo ano tem muita dificuldade em ter uma visão ampla de como estão as notas do SISU como um todo.

p.s: _Caso o site não carregue os cursos ou você não consiga acessar o link do Swagger espere 1 min e tente novamente pois estamos usando o plano free do heroku e ele hiberna a aplicação caso não tenha acesso em 30 min_ 
