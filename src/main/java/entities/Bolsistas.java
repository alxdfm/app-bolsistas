package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bolsistas implements Comparable<Bolsistas>{

    private String nm_bolsista;
    private String cpf_bolsista;
    private String nm_entidade_ensino;
    private String me_referencia;
    private String an_referencia;
    private String sg_diretoria;
    private String sg_sistema_origem;
    private String cd_modalidade_sgb;
    private String ds_modalidade_pagamento;
    private String cd_moeda;
    private String vl_bolsista_pagamento;

    @Override
    public int compareTo(Bolsistas o) { //utilizado para ordenar a lista pelo vl_bolsista_pagamento

        Integer vl_bolsistaThis = Integer.parseInt(this.vl_bolsista_pagamento);
        Integer vl_bolsistaO = Integer.parseInt(o.getVl_bolsista_pagamento());

        if (vl_bolsistaThis > vl_bolsistaO){
            return -1;
        }
        if (vl_bolsistaThis < vl_bolsistaO){
            return 1;
        }
        else{
            return 0;
        }
    }
}
