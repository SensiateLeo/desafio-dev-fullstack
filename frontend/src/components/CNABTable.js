import React, { useEffect, useState } from 'react';
import moment from 'moment';
import databaseService from '../service/database.service'
import './CNABTable.css'

const CNABTable = ({ uploadedCnabs }) => {
    const [cnabs, setCnabs] = useState([]);
    const [isLoading, setIsLoading] = useState(false);

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
                        <td>{cnab?.tipo}</td>
                        <td>R$ {cnab?.valor}</td>
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
      </div>
      )
    );
  };

export default CNABTable;

