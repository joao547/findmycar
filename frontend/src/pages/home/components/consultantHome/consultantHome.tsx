/* eslint-disable @typescript-eslint/no-explicit-any */
import { useEffect, useState } from 'react';
import { api } from '../../../../service/api';

export function ConsultantHome() {
  const [consultants, setConsultants] = useState<any>();
  useEffect(() => {
    const token = localStorage.getItem('@token');

    async function fetchData() {
      const { data } = await api.get('/api/consultor/todos', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setConsultants(data);
    }

    fetchData();
  }, []);
  return (
    <div>
      <ol>
        {consultants?.map((c: any) => (
          <li key={c.id} className='border p-4 shadow-md bg-slate-50'>
            {JSON.stringify(c)}
          </li>
        ))}
      </ol>
    </div>
  );
}
