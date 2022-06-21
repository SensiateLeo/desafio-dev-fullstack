import React, { useState } from 'react';
import CNABTable from '../components/CNABTable';
import FileInput from '../components/FileInput';
import "./Main.css";

const Main = () => {
    const [uploadedCnabs, setUploadedCnabs] = useState(false);
    return (
        <div className='mainContainer'>
            Desafio Full Stack
            <div className='mainBody'>
                {!uploadedCnabs && (
                    <FileInput setUploadedCnabs={setUploadedCnabs} />
                )}
                {uploadedCnabs && (
                    <CNABTable uploadedCnabs={uploadedCnabs} />
                )}
            </div>
        </div>
    )
}

export default Main;