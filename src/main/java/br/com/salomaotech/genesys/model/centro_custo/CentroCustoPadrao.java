package br.com.salomaotech.genesys.model.centro_custo;

import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.LinkedHashMap;
import java.util.Map;

public class CentroCustoPadrao {

    private final Map<String, String> dados = new LinkedHashMap();

    public CentroCustoPadrao() {

        dados.put("1.1", "Despesas administrativas e comerciais");
        dados.put("1.1.1", "Aluguel");
        dados.put("1.1.2", "Assessorias e associações");
        dados.put("1.1.3", "Cartório");
        dados.put("1.1.4", "Combustivel e translados");
        dados.put("1.1.5", "Confraternizações");
        dados.put("1.1.6", "Contabilidade");
        dados.put("1.1.7", "Correios");
        dados.put("1.1.8", "Cursos e treinamentos");
        dados.put("1.1.9", "Distribuição de lucros");
        dados.put("1.1.10", "Empréstimos");
        dados.put("1.1.11", "Encargos funcionários - 13o salário");
        dados.put("1.1.12", "Encargos funcionários - alimentação");
        dados.put("1.1.13", "Encargos funcionários - assist. médica e odontol.");
        dados.put("1.1.14", "Encargos funcionários - exames pré e demissionais");
        dados.put("1.1.15", "Encargos funcionários - FGTS");
        dados.put("1.1.16", "Encargos funcionarios - horas extras");
        dados.put("1.1.17", "Encargos funcionários - INSS");
        dados.put("1.1.18", "Encargos funcionários - vale transporte");
        dados.put("1.1.19", "Encargos - rescisões trabalhistas");
        dados.put("1.1.20", "Energia elétrica + água");
        dados.put("1.1.21", "Impostos - alvará");
        dados.put("1.1.22", "Impostos - coleta de lixo");
        dados.put("1.1.23", "Impostos - IPTU");
        dados.put("1.1.24", "Impostos - PIS");
        dados.put("1.1.25", "Licença ou aluguel de softwares");
        dados.put("1.1.26", "Limpeza");
        dados.put("1.1.27", "Manutenção equipamentos");
        dados.put("1.1.28", "Marketing e publicidade");
        dados.put("1.1.29", "Material de escritório");
        dados.put("1.1.30", "Material reforma");
        dados.put("1.1.31", "Remuneração funcionários");
        dados.put("1.1.32", "Segurança");
        dados.put("1.1.33", "Supermercado");
        dados.put("1.1.34", "Telefonia e internet");
        dados.put("1.1.35", "Transportadora");
        dados.put("1.1.36", "Viagens");
        dados.put("1.1.37", "Devolução de vendas");
        dados.put("1.2", "Despesas de produtos vendidos");
        dados.put("1.2.1", "Comissão de vendedores");
        dados.put("1.2.2", "Compras");
        dados.put("1.2.3", "Impostos - COFINS");
        dados.put("1.2.4", "Impostos - CSSL");
        dados.put("1.2.5", "Impostos - ICMS");
        dados.put("1.2.6", "Impostos - importação IPI");
        dados.put("1.2.7", "Impostos - IRPJ");
        dados.put("1.2.8", "Impostos - ISS");
        dados.put("1.3", "Despesas financeiras");
        dados.put("1.3.1", "Despesas bancárias");
        dados.put("1.4", "Investimentos");
        dados.put("1.4.1", "Aquisição de equipamentos");
        dados.put("1.5", "Outras despesas");
        dados.put("1.5.1", "Adiantamento - funcionários");
        dados.put("1.5.2", "Ajuste de caixa");
        dados.put("2.1", "Receitas de vendas");
        dados.put("2.1.1", "Vendas de produtos");
        dados.put("2.1.2", "Vendas no balcão");
        dados.put("2.1.3", "Prestações de serviços");
        dados.put("2.1.4", "Contratos de serviços");
        dados.put("2.1.5", "Locação de equipamentos");
        dados.put("2.2", "Receitas financeiras");
        dados.put("2.2.1", "Aplicações financeiras");
        dados.put("2.3", "Outras receitas");
        dados.put("2.3.1", "Ajuste de caixa");
        dados.put("2.3.2", "Devolução de adiantamento");

    }

    public void cadastrar() {

        JPQL jpql;

        for (String codigo : dados.keySet()) {

            jpql = new JPQL(new CentroCustoModelo());
            jpql.addParametroIgual("codigo", codigo);

            if (new Repository(new CentroCustoModelo()).countTodos(jpql.getCondicaoWhere()) == 0) {

                CentroCustoModelo centroCustoModelo = new CentroCustoModelo();
                centroCustoModelo.setCodigo(codigo);
                centroCustoModelo.setNome(dados.get(codigo));
                new Repository(centroCustoModelo).save();

            }

        }

    }

}
