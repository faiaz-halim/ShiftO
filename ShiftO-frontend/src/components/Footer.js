import React from 'react';

const Footer = () => {
  const year = new Date().getFullYear();

  return (
    <footer style={{ textAlign: 'center', padding: '20px', marginTop: '30px', backgroundColor: '#f0f0f0' }}>
      <p>ShiftO © {year}</p>
    </footer>
  );
};

export default Footer;
