package provacrud;

//requisitos

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;


//desenvolva um sistema em Java com persistencia de dados em memoria, famosos CRUD
//O sistema deve apresentar um menu ao usuario , refletindo a situacao de um estoque do e-commerce da Joana da loja JôJoias
//No menu deve ser possivel add produto, excluir, consultar e atualizar
//Quando for comprado mais produtos ela precisa aumentar qtnd de prod disponivel
//Quando o usuario consultar a lista de produtos da loja, sendo mostrado valor unitario, quantidade disponivel e quantidade reservada
//O usuario pode pedir pra reservar um prod
//oo sistema nao controla pedidos, mas deve ser possivel cancelar uma reserva 
//saida do estoque reserva para venda


//nome String
//valorUniario Double
//qtdDisp Int
//qtdReserv Int
//
//public void entradaProd(int qtdEntrada){
//Produto p = new Produto(nome, valor, qtdDisp, QtdReserv);
//}
class Produto {
        private String nomeProd;
        private String quantidadeProd;
        private String valorProd;
        private String qtndReservada;


        public Produto(String nomeProd, String quantidadeProd, String valorProd) {
            this.nomeProd = nomeProd;
            this.quantidadeProd = quantidadeProd;
            this.valorProd = valorProd;
        }
                
        public String getNomeProd(){
            return nomeProd;
        }
        public String getQuantidadeProd(){
            return quantidadeProd;
        }
        public String getValorProd(){
            return valorProd;
        }
        public String getQtndReservada(){
            return qtndReservada;
        }
}

    
public class Main {
    private final Map<String, Produto> produtos;

    public Main() {
        produtos = new HashMap<>();
    }
    
    public void addProduto() {
        String nomeProd = JOptionPane.showInputDialog("Digite o nome do produto:");
        String quantidadeProd = JOptionPane.showInputDialog("Digite a quantidade que deseja adicionar:");
        String valorProd = JOptionPane.showInputDialog("Digite o valor do produto:");
       
        Produto produto = new Produto(nomeProd, quantidadeProd, valorProd);
        produtos.put(nomeProd.substring(0, nomeProd.length()), produto);
        JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
    }
    
    public void consultarProd(){
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto encontrado.");
        } else {
            StringBuilder mensagem = new StringBuilder("Produtos:\n");
            for (Produto produto : produtos.values()) {
                mensagem.append("Nome Produto: ").append(produto.getNomeProd()).append("\n");
                mensagem.append("Quantidade Produto: ").append(produto.getQuantidadeProd()).append("\n");
                mensagem.append("Valor Produto: R$").append(produto.getValorProd()).append(",00\n");
                mensagem.append("--------------------\n");
            }
            JOptionPane.showMessageDialog(null, mensagem.toString());
        }
    }
    public void atualizarProd() {
        String nmProd = JOptionPane.showInputDialog("Digite o nome do produto que deseja atualizar:");

        Produto produto = produtos.get(nmProd);
        produtos.remove(nmProd, produto);
        if (produto != null) {
            String novoNomeProd = JOptionPane.showInputDialog("Digite o novo nome do produto:");
            String novaQuantidadeProd = JOptionPane.showInputDialog("Digite a nova quantidade disponivel do produto:");
            String novoValorProd = JOptionPane.showInputDialog("Digite o novo valor do produto:");
            
            produto = new Produto(novoNomeProd, novaQuantidadeProd, novoValorProd);
            
            produtos.put(nmProd, produto);
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        }
    }
    public void deletarProd() {
        String nmProd = JOptionPane.showInputDialog("Digite o nome do produto que deseja excluir:");

        Produto produto = produtos.remove(nmProd);
        if (produto != null) {
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "produto não encontrado.");
        }
    }
    public void reservarProduto(){
        String reservaProduto = JOptionPane.showInputDialog("Qual produto voce deseja reservar: ");
         
        Produto produto = produtos.get(reservaProduto);
        if (produto != null) {
            String produtosReservados = JOptionPane.showInputDialog("Quantas unidades voce deseja reservar:");
            

            
            
            JOptionPane.showMessageDialog(null, "Produto reservado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        }
    }
    
    public static void main(String[] args) {
        Main crudProdutos = new Main();
        int escolha;

        do {
            String[] opcoes = { "Adicionar produtos", "Consultar produtos", "Atualizar produtos", "Excluir produtos", "Reservar Produto", "Sair" };
            escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0: 
                    crudProdutos.addProduto();
                break;
                case 1: 
                    crudProdutos.consultarProd();
                break;
                case 2: 
                    crudProdutos.atualizarProd();
                break;
                case 3: 
                    crudProdutos.deletarProd();
                break;
                case 4: 
                    crudProdutos.reservarProduto();
                break;
                case 5: 
                    JOptionPane.showMessageDialog(null, "Saindo...");
                break;
                default: 
                    JOptionPane.showMessageDialog(null, "Escolha inválida.");
                break;
            }
        } while (escolha != 5);
    }
    
}
