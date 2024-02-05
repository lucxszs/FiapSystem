package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class FiapDao {

    private EntityManager manager;

    private void conectar() {
        // Método para estabelecer a conexão com o banco de dados utilizando JPA
        manager = Persistence.createEntityManagerFactory("FiapSystemPU").createEntityManager();
    }

    public int cadastrarFuncionario(Funcionario funcionario) {
        conectar(); // Conecta ao banco de dados

        try {
            manager.getTransaction().begin(); // Inicia a transação
            manager.persist(funcionario); // Persiste o funcionário no banco de dados
            manager.getTransaction().commit(); // Finaliza a transação
            return 1; // Sucesso ao cadastrar
        } catch (RollbackException erro) {
            return 2; // Funcionário já cadastrado
        } catch (Exception erro) {
            return 0; // Erro ao cadastrar o funcionário
        }
    }

    public int excluirFuncionario(String matricula) {
        conectar(); // Conecta ao banco de dados

        Funcionario funcionario = manager.find(Funcionario.class, matricula); // Procura o funcionário pelo ID
        if (funcionario == null) {
            return 0; // Se não encontrar, retorna 0 (não encontrado)
        } else {
            manager.getTransaction().begin(); // Inicia a transação
            manager.remove(funcionario); // Remove o funcionário do banco de dados
            manager.getTransaction().commit(); // Finaliza a transação
            return 1; // Sucesso ao excluir
        }
    }

    public List<Funcionario> listaDeFuncionarios() {
        conectar(); // Conecta ao banco de dados

        try {
            List<Funcionario> funcionarios = manager.createNamedQuery("Funcionario.findAll", Funcionario.class).getResultList(); // Obtém a lista de funcionários
            return funcionarios; // Retorna a lista de funcionários encontrados
        } catch (Exception e) {
            System.err.println("Erro ao listar os funcionários: " + e.getMessage());
            e.printStackTrace();
            return null; // Retorna null em caso de erro ao listar os funcionários
        } finally {
            manager.close(); // Fecha o EntityManager
        }
    }
}
