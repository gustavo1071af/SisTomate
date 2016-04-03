# Sistomate
This paper presents an approach focused on improving the quality of the tomato crops. Thus, to achieve such goal, we designed and implemented a computational environment to support decision-making related to the tomato production. Our focus is to automate the detection of tomatoes late blight, with the aid of pre-processing images.
# Respeite os diretos autorais!

- Guia de instalação:
1)Primeiramente, execute o script sql "Banco_projeto_v1.sql" que está na raiz do projeto para criar o bd e inserir os registros de teste.
2)Execute os outros scripts de alterações que estão em docs > database, em ordem crescente as versões.(Recomendado usar o mysql workbench)
obs. Estes scripts não definem qual é o db a ser usado(e devem continuar assim), então fique bem atento a qual db você irá executar.
3)Caso houver erros de compilação devido a quebra de bibliotecas, as bibliotecas estão em dlist > lib. Logo,
 delete as bibliotecas que estão quebradas e adicione-as novamente.
4)configure o arquivo Conn.java (dentro da pasta DAO) para os parametros do mysql  caso necessário.
4)Ligue o mysql e execute o programa.

- Alterando o modelo:
Processo de alteração do banco de dados do sistema.
1)Caso for mudar o modelo do sistema, deve-se criar uma arquivo .sql em docs > database guardando apenas o script das alterações.
2)Verifique seu email para ver se alguém está prestes a fazer modificação no modelo, e ver qual será a versão
3)Mande um email para os outros integrantes do projeto com o assunto "AVISO - ALTERAÇÃO DA BASE: TO V<?>"; Ex: AVISO - ALTERAÇÃO DA BASE: PARA V2
4)Siga o padrão e a numeração das versões. ex: se o último arquivo é v1_to_v2, a nova alteração será v2_to_v3.
obs. Não há controle de versão no banco de dados, então fique atento para não quebrar o sistema dos outros integrantes.
5)Teste o script e verifique se está funcionando corretamente, isso é muito importante.
6)Caso a sua alteraação tiver muito impacto no sistema, *faça um dump do bd do projeto e crie outro para evitar risco*.

- Alteração do Sistema.
Caso for alterar o código do sistema, segue o processo. (Apenas para alterações avulsas, ou seja, sem uso de backlog)
1)Vá no Log_de_Alteração na raiz do projeto e adicione na ultima linha : SISTOM-xxxx: (xxxx é um auto incremento, e deve seguir a ordem da ultima inserção).
ex: se a ultima linha existir um "SISTOM-10" vc vai pular a linha e inserir o "SISTOM-11:"
Obs.Esse log vai guardar o código da alteração e seu respectivo comentário.
2)Após inserir a nova linha, commit(com a mensagem do commit "SISTOM-xxxx INIT") e de push.(para os outros integrantes saberem que vc está fazendo uma alteração)
3) Durante a alteração, e se achar importante, commente no códico que foi feito a alteração o código referente a ela. 
ex: //SISTOM-11
    if (condition){
        ...
    }
4)Uma boa prática, é comentar o que é importante na alteração, o porque que foi feito a alteração e qual foi o objetivo, etc...
este comentário deve seguir no código da alteração no Log_de_Alterações. Ex: SYSTOM-11: "Foi adicionado uma função if para..."
5)Teste sua alteração(Muito Importante!).
6)*SEMPRE DE FECTH e PULL NO PROJETO ANTES DE COMMITAR*, para caso alguem tenha commitado antes de você. 
7)Faça os merges se necessário(boa prática é fazer o merge manualmente depois de dar Fetch, o pull força merge, ou seja, nem sempre vai ser certo).
obs. Cuidado com os merges para não remover códigos dos outros integrantes. 
8)Faça o commit(com a mensagem do commit "SISTOM-xxxx") e push.


*Caso tiver com duvidas na utilização do git no netbeans, estudar o link:
https://netbeans.org/kb/docs/ide/git.html
https://git-scm.com/book/pt-br/v1/Git-Essencial-Trabalhando-com-Remotos