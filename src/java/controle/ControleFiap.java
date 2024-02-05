package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.FiapDao;

@WebServlet(name = "ControleFiap", urlPatterns = {"/ControleFiap"})
public class ControleFiap extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Declaração de variáveis
        String flag, mensagem;

        // Obtenção do parâmetro "flag" da requisição
        flag = request.getParameter("flag");
        if (flag.equals("cadastrar")) {

            // Obtenção de parâmetros do formulário de cadastro
            String name, tel, email, carg, mat;

            mat = request.getParameter("matricula");
            name = request.getParameter("nome");
            email = request.getParameter("email");
            tel = request.getParameter("telefone");
            carg = request.getParameter("cargo");

            // Criação de um objeto Funcionario e configuração de seus atributos
            Funcionario funcionario = new Funcionario();
            funcionario.setMatricula(mat);
            funcionario.setNome(name);
            funcionario.setEmail(email);
            funcionario.setTelefone(tel);
            funcionario.setCargo(carg);

            // Chamada do método cadastrarFuncionario da classe FiapDao para cadastrar o funcionário
            int resultado = new FiapDao().cadastrarFuncionario(funcionario);
            switch (resultado) {
                case 1:
                    mensagem = "Funcionario cadastrado com exito!";
                    break;
                case 2:
                    mensagem = "Funcionario já cadastrado!";
                    break;
                default:
                    mensagem = "Ocorreu um erro ao cadastrar o funcionario!";
                    break;
            }

            // Definição de mensagem para exibição na página
            request.setAttribute("mensagem", mensagem);

            // Redirecionamento para a página de exibição de mensagens
            RequestDispatcher disp = request.getRequestDispatcher("view/mensagem.jsp");
            disp.forward(request, response);

        } else if (flag.equals("excluir")) {

            // Parte do código para excluir um funcionário
            String mat = request.getParameter("matricula");
            int resultado = new FiapDao().excluirFuncionario(mat);
            if (resultado == 1) {
                mensagem = "Sua matricula foi excluida com exito";
            } else {
                mensagem = "Houve um problema ao excluir a matricula tente novamente";
            }

            request.setAttribute("mensagem", mensagem);

            RequestDispatcher disp = request.getRequestDispatcher("view/mensagem.jsp");
            disp.forward(request, response);

        } else if (flag.equals("listar")) {

            // Parte do código para listar funcionários
            List<Funcionario> listarFuncionarios = new FiapDao().listaDeFuncionarios();

            System.out.println("Número de funcionários recebidos no Servlet ControleLibrary: " + (listarFuncionarios != null ? listarFuncionarios.size() : 0));

            request.setAttribute("listarFuncionarios", listarFuncionarios);

            RequestDispatcher disp = request.getRequestDispatcher("view/listaFiap_fun.jsp");
            disp.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
