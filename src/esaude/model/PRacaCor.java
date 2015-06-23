package esaude.model; 

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.*;
import java.util.*;

@Entity 
@Table(name="p_raca_cor")
public class PRacaCor implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "co_raca")
   private String coRaca;

   @Column(name="ds_raca")
   private String dsRaca;


   public  String  getCoRaca() { 
      return coRaca;
   }
   public void  setPRacaCor(String coRaca) { 
      this.coRaca = coRaca;
   }

   public String getDsRaca() { 
      return dsRaca;
   }
   public void  setDsRaca(String dsRaca) { 
      this.dsRaca = dsRaca;
   }

}