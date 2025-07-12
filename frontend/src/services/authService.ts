import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

const authService = {
  async login(credentials: { username: string; password: string }) {
    const response = await axios.post(`${API_URL}/auth/login`, credentials);
    return response.data;
  },

  async register(userData: { username: string; email: string; password: string; nickname?: string }) {
    const response = await axios.post(`${API_URL}/auth/register`, userData);
    return response.data;
  },

  async getCurrentUser() {
    const token = localStorage.getItem('token');
    if (!token) {
      throw new Error('No token found');
    }
    
    const response = await axios.get(`${API_URL}/auth/me`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  },
};

export { authService }; 