const useLogin = () => {
  const makeLogin = async ({
    email,
    password,
  }: {
    email: string;
    password: string;
  }) => {
    try {
      const body = JSON.stringify({ email, password });

      const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        body,
        headers: {
          "Content-Type": "application/json;charset=UTF-8"
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
