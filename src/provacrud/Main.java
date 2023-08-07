package provacrud;

//requisitos

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;



class Produto {
        private String nome;
        private double valorUnitario;
        private int quantidadeDisponivel;
        private int quantidadeReservada;


        public Produto(String nome, double valorUnitario, int quantidadeDisponivel) {
            this.nome = nome;
            this.valorUnitario = valorUnitario;
            this.quantidadeDisponivel = quantidadeDisponivel;
            this.quantidadeReservada = 0;
        }
                
        public String getNome() {
            return nome;
        }

        public double getValorUnitario() {
            return valorUnitario;
        }

        public int getQuantidadeDisponivel() {
            return quantidadeDisponivel;
        }

        public int getQuantidadeReservada() {
            return quantidadeReservada;
        }

        public void reservar(int quantidade) {
            if (quantidade <= quantidadeDisponivel) {//verifica se a quantidade que deseja reservar e menor ou igual a quantidade disponivel
                quantidadeDisponivel -= quantidade;
                quantidadeReservada += quantidade;
            } else {
                JOptionPane.showMessageDialog(null, "Quantidade indisponível para reserva.");
            }
        }

        public void cancelarReserva(int quantidade) {
            if (quantidade <= quantidadeReservada) {//verifica se a quantidade que deseja tirar do reservado é menor que a quantidade pedida
                quantidadeDisponivel += quantidade;
                quantidadeReservada -= quantidade;
            } else {
                JOptionPane.showMessageDialog(null, "Quantidade a cancelar é maior do que a quantidade reservada.");
            }
        }

        public void registrarSaida(int quantidade) {
            if (quantidade <= quantidadeReservada) {//verifica se a qtnd que deseja tirar e maior ou igual a quantidade que esta reservada
                quantidadeReservada -= quantidade;
                if (quantidadeReservada == 0) {
                    quantidadeDisponivel -= quantidade;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Quantidade de saída é maior do que a quantidade reservada.");
            }
        }
}

    
public class Main {
    private Map<String, Produto> estoque;

    public Main() {
        estoque = new HashMap<>();
    }
    
    public void addProduto() {
        String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
        int quantidadeDisponivel = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade disponível do produto:"));
        double valorUnitario = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor unitário do produto:"));
       
        Produto produto = new Produto(nome, valorUnitario, quantidadeDisponivel);
        estoque.put(nome, produto);
        
        
        JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
    }
    
    public void consultarProd(){
        StringBuilder mensagem = new StringBuilder("Lista de Produtos:\n");
            for (Produto produto : estoque.values()) {
                mensagem.append("Nome: ").append(produto.getNome()).append("\n");
                mensagem.append("Valor Unitário: ").append(produto.getValorUnitario()).append("\n");
                mensagem.append("Quantidade Disponível: ").append(produto.getQuantidadeDisponivel()).append("\n");
                mensagem.append("Quantidade Reservada: ").append(produto.getQuantidadeReservada()).append("\n");
                mensagem.append("--------------------\n");
            }

            JOptionPane.showMessageDialog(null, mensagem.toString());
        }

    public void deletarProd() {
        String nmProd = JOptionPane.showInputDialog("Digite o nome do produto que deseja excluir:");

        Produto produto = estoque.remove(nmProd);
        if (produto != null) {
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "produto não encontrado.");
        }
    }
    
    public void reservarProduto(){
        String nome = JOptionPane.showInputDialog("Digite o nome do produto para reserva:");
        Produto produto = estoque.get(nome);

        if (produto != null) {
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade para reserva:"));
            produto.reservar(quantidade);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        }
    }
    
    public void cancelarReserva() {
        String nome = JOptionPane.showInputDialog("Digite o nome do produto para cancelar a reserva:");
        Produto produto = estoque.get(nome);

        if (produto != null) {
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade para cancelar a reserva:"));
            produto.cancelarReserva(quantidade);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        }
    }

    public void registrarSaida() {
        String nome = JOptionPane.showInputDialog("Digite o nome do produto para registrar a saída:");
        Produto produto = estoque.get(nome);

        if (produto != null) {
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade para registrar a saída:"));
            produto.registrarSaida(quantidade);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        }
    }
    
    public static void main(String[] args) {
        Main sistema = new Main();
        int escolha;

        do {
            String[] opcoes = { "Adicionar Produto", "Listar Produtos", "Reservar Produto",
                    "Cancelar Reserva", "Registrar Saída", "Sair" };

            escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0:
                    sistema.addProduto();
                    break;
                case 1:
                    sistema.consultarProd();
                    break;
                case 2:
                    sistema.reservarProduto();
                    break;
                case 3:
                    sistema.cancelarReserva();
                    break;
                case 4:
                    sistema.registrarSaida();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Escolha inválida.");
            }
        } while (escolha != 5);
    }
    
}