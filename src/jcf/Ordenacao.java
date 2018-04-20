package jcf;

public enum Ordenacao {

    PRECO_ASC,
    PRECO_DESC, 
    TITULO_PRECO_CRESC, 
    BANDA_PRECO_DESC;

    public static Ordenacao getOrdenacao(int id) {
        switch (id) {
            case 0:
                //Preco crescente
                return Ordenacao.PRECO_ASC;
            case 1:
                //Preco decrescente
                return Ordenacao.PRECO_DESC;
            case 2:
                //Ordem de titulo e preco crescente
                return Ordenacao.TITULO_PRECO_CRESC;
            case 3:
                //Ordem de banda e preco decrescente
                return Ordenacao.BANDA_PRECO_DESC;
            default:
                //Preco crescente
                return Ordenacao.PRECO_ASC;
        }
    }

    public static int getOrdenacaoID(Ordenacao o) {
        switch (o) {
            case PRECO_ASC:
                //Preco crescente
                return 0;
            case PRECO_DESC:
                //Preco decrescente
                return 1;
            case TITULO_PRECO_CRESC:
                //Ordem de titulo e preco crescente
                return 2;
            case BANDA_PRECO_DESC:
                //Ordem de banda e preco decrescente
                return 3;
            default:
                //Preco crescente
                return 0;
        }
    }
}
