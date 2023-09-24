/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
   private conectaDAO conexao;
   private Connection conn;
   PreparedStatement prep;
   ResultSet resultset;
   ArrayList<ProdutosDTO> listagem = new ArrayList<>();

 public ProdutosDAO() {     
    this.conexao = new conectaDAO();
    this.conn = this.conexao.connectDB();}
    

    
    public void cadastrarProduto (ProdutosDTO produto){
        
    String sql = "INSERT INTO produtos(nome, valor, status) VALUES " + "(?, ?, ?)";
        try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome()); 
        stmt.setDouble(2, produto.getValor());
        stmt.setString(3, produto.getStatus()); 
// 
        stmt.execute();
        
        } catch (Exception e) {
        System.out.println("Erro ao inserir Produto: " + e.getMessage());
}
    }

        
    
    
    public List<ProdutosDTO> listarProdutos(){

        
  
                String sql = "SELECT * FROM produtos";
                
                try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();            
                    
                    List<ProdutosDTO> listaPod = new ArrayList<>();
                    
                    while (rs.next()) { 
                        ProdutosDTO produtosDTO = new ProdutosDTO();
                        
                        produtosDTO.setId(rs.getInt("id"));
                        produtosDTO.setNome(rs.getString("nome"));
                        produtosDTO.setValor(rs.getDouble("valor"));
                        produtosDTO.setStatus(rs.getString("status"));
                            
                        listaPod.add(produtosDTO);    
                    }
                    return listaPod;
                    
                 
                } catch (Exception e) {
                    return null;
                }

    }
        public void venderProduto (ProdutosDTO produto){
        
    String sql =  "update produtos set status='Vendido' where id=?";  
    try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setInt(1, Integer.valueOf(produto.getId())); 

// 
        stmt.execute();
        
        } catch (Exception e) {
        System.out.println("Erro ao inserir Produto: " + e.getMessage());
}
    }
    
    
        
}

