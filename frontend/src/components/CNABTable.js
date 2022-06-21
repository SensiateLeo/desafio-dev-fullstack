import React, { useEffect, useState } from 'react';
import moment from 'moment';

import databaseService from '../service/database.service'
import { tipos } from '../helpers/enums';

import './CNABTable.css'

const CNABTable = ({ uploadedCnabs }) => {
    const [cnabs, setCnabs] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [total, setTotal] = useState(0);

    const indicesSoma = ['1', '4', '5', '6', '7', '8'];
    const indicesSubt = ['2', '3', '9'];

    useEffect(() => {
        if (cnabs.length === 0 && uploadedCnabs) {
            setIsLoading(true);
            databaseService.getCNAB().then(res => {
                setCnabs(res?.data);
            })
            .finally(() => setIsLoading(false));
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [uploadedCnabs]);

    useEffect(() => {
      if (cnabs.length > 0) {
        cnabs.forEach(cnab => {
          if (indicesSoma.includes(cnab?.tipo)) setTotal(total + parseInt(cnab?.valor, 10));
          else if (indicesSubt.includes(cnab?.tipo)) setTotal(total - parseInt(cnab?.valor, 10));
        })
      }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [cnabs]);
    
    return (
      isLoading ? (
        <div className="spinner">
          <div className="loading" />
        </div>
      ) : (
      <div className='tableContainer'>
          <table className='table'>
            <thead className='header'>
              <tr>
                  <th>Tipo</th>
                  <th>Valor</th>
                  <th>CPF</th>
                  <th>Nº Cartão</th>
                  <th>Data</th>
                  <th>Hora</th>
                  <th>Nome da Loja</th>
                  <th>Dono da Loja</th>
              </tr>
            </thead>
            <tbody className='body'>
              {cnabs?.map((cnab, key) => {
                return (
                  <tr key={key}>
                      <td>{tipos[cnab?.tipo]}</td>
                      <td>R$ {cnab?.valor},00</td>
                      <td>{cnab?.cpf}</td>
                      <td>{cnab?.cartao}</td>
                      <td>{moment(cnab?.data).format('DD/MM/YYYY')}</td>
                      <td>{cnab?.hora}</td>
                      <td>{cnab?.nomeLoja}</td>
                      <td>{cnab?.donoLoja}</td>
                  </tr>
                )
              })}
            </tbody>
	        </table>
          <table className='table'>
            <tr key='total'>
              <td>Saldo em conta: R$ {total},00</td>
            </tr>
          </table>
      </div>
      )
    );
  };

export default CNABTable;

