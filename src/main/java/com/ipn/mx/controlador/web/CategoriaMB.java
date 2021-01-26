/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;



/**
 *
 * @author darkdestiny
 */
@ManagedBean(name = "categoriaMB")
@SessionScoped
public class CategoriaMB extends BaseBean implements Serializable{
    private final CategoriaDAO dao = new CategoriaDAO();
    private CategoriaDTO dto;
    private List<CategoriaDTO> listaCategorias;
    
    
    /**
     * Creates a new instance of CategoriaMB
     */
    public CategoriaMB() {
    }
    
    @PostConstruct
    public void init(){
        listaCategorias = new ArrayList<>();
        listaCategorias = dao.readAll();
    }
    
    public String prepareAdd(){
        dto = new CategoriaDTO();
        setAccion(ACC_CREAR);
        return "/categoria/categoriaForm?faces-redirect=true";
    }
    
    public String prepareUpdate(){
        setAccion(ACC_ACTUALIZAR);
        return "/categoria/categoriaForm?faces-redirect=true";
    }
    
    public String prepareIndex(){
        init();
        return "/categoria/listadoCategorias?faces-redirect=true";
    }   
    
    public String back(){
        return prepareIndex();
    }
    
    public boolean validate(){
        boolean valido = true; 
        // Las llamadas a las validaciones 
        return valido;
    }
    
    public String add(){
        boolean valido = validate();
        if (valido){
            dao.create(dto);
            return prepareIndex();
        }else {
            return prepareAdd();
        }
    }
    
    public String update(){
        boolean valido = validate();
        if (valido){
            dao.update(dto);
            return prepareIndex();
        }else {
            return prepareUpdate();
        }
    }
    
    public String delete(){
            dao.delete(dto);
            return prepareIndex();
    }
    
    public void selecionarCategoria(ActionEvent event){
            String claveSel = (String) FacesContext.getCurrentInstance().
                    getExternalContext().
                    getRequestParameterMap().get("claveSel");
            dto = new CategoriaDTO();
            dto.getEntidad().setIdCategoria(Integer.parseInt(claveSel));
            try{
                dto = dao.read(dto);
            }catch(Exception e){
                e.printStackTrace();
            }
    }

    public CategoriaDTO getDto() {
        return dto;
    }

    public void setDto(CategoriaDTO dto) {
        this.dto = dto;
    }

    public List<CategoriaDTO> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CategoriaDTO> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    
    
}