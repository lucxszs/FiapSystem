<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Funcionario"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Estoque de Produto</title>
        <link rel="stylesheet" href="style/config.css">
    </head>
    <body>
        <header>
            <a href="#" class="logo">FIAP</a>
            <ul>
                <li><a href="index.html">Cadastro</a></li>
                <li><a href="" class="active">Lista</a></li>
            </ul>
        </header>

        <section id="registration">
            <h2 id="text">Tabela de Funcionarios</h2>
            <table class="bet-table">
                <thead>
                    <tr>
                        <th>Matricula</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Telefone</th>
                        <th>Cargo</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Funcionario> listarFuncionarios = (List<Funcionario>) request.getAttribute("listarFuncionarios");

                        if (listarFuncionarios != null && !listarFuncionarios.isEmpty()) {
                            for (Funcionario funcionario : listarFuncionarios) {
                    %>
                    <tr>
                        <td><%= funcionario.getMatricula()%></td>
                        <td><%= funcionario.getNome()%></td>
                        <td><%= funcionario.getEmail()%></td>
                        <td><%= funcionario.getTelefone()%></td>
                        <td><%= funcionario.getCargo()%></td>
                        <td><a class="button" href="ControleFiap?flag=excluir&matricula=<%= funcionario.getMatricula()%>">Excluir</a></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6">Não há funcionários cadastrados.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </section>
    </body>
</html>