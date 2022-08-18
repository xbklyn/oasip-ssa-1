module.exports = {
  content: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  theme: {
    extend: {},
    screens: {
      sm: '480px',
      md: '768px',
      lg: '976px',
      xl: '1440px',
    },
    // screens: {
    //     'tablet': '640px',
    //     // => @media (min-width: 640px) { ... }

    //     'laptop': '1024px',
    //     // => @media (min-width: 1024px) { ... }

    //     'desktop': '1280px',
    //     // => @media (min-width: 1280px) { ... }
    // },
  },
  plugins: [],
};
