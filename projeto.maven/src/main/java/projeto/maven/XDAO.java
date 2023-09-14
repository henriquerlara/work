package projeto.maven;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XDAO {
    private Connection conexao;

    public XDAO() {
        conectar();
    }

    public void conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "segundo";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
        String username = "henriquerlara";
        String password = "adidasf50";

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão efetuada com o PostgreSQL!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
    }

    public void close() {
        try {
            conexao.close();
            System.out.println("Conexão fechada com o PostgreSQL!");
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }

    public List<X> listarTodos() {
        List<X> xList = new ArrayList<>();
        try {
            Statement st = conexao.createStatement();
            String sql = "SELECT * FROM x";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                xList.add(new X(id, nome, idade));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.err.println("Erro ao listar: " + e.getMessage());
        }
        return xList;
    }

    public boolean inserir(X x) {
        boolean status = false;
        try {
            String sql = "INSERT INTO x (nome, idade) VALUES (?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, x.getNome());
            ps.setInt(2, x.getIdade());
            int rowsAffected = ps.executeUpdate();
            ps.close();
            status = rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir: " + e.getMessage());
        }
        return status;
    }

    public boolean excluir(int id) {
        boolean status = false;
        try {
            String sql = "DELETE FROM x WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            ps.close();
            status = rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir: " + e.getMessage());
        }
        return status;
    }

    public boolean atualizar(X x) {
        boolean status = false;
        try {
            String sql = "UPDATE x SET nome = ?, idade = ? WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, x.getNome());
            ps.setInt(2, x.getIdade());
            ps.setInt(3, x.getId());
            int rowsAffected = ps.executeUpdate();
            ps.close();
            status = rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar: " + e.getMessage());
        }
        return status;
    }
}