const useLogin = () => {
  const makeLogin = async ({
    email,
    password,
  }: {
    email: string;
    password: string;
  }) => {
    try {
      debugger;
      // const body = `{ "email": "${email}", "password": "${password}" }`;
      const body = JSON.stringify({ email, password });

      console.log(body);
      const response = await fetch('http://localhost:8080/api/auth/login', {
        mode: 'no-cors',
        method: 'POST',
        body,
        headers: {
          'Content-Type': 'application/json',
          Accept: 'application/json',
        },
      });

      console.log({ response });
    } catch (err) {
      console.log(err);
    }
  };

  return {
    makeLogin,
  };
};

export { useLogin };
