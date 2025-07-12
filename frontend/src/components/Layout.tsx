import React from 'react';
import { Layout as AntLayout, Menu, Button, Space } from 'antd';
import { useNavigate, Outlet } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../store';
import { logout } from '../store/authSlice';

const { Header, Content } = AntLayout;

const Layout: React.FC = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { user } = useSelector((state: RootState) => state.auth);

  const handleLogout = () => {
    dispatch(logout());
    navigate('/login');
  };

  const menuItems = [
    {
      key: '/',
      label: '清洁公司',
      onClick: () => navigate('/'),
    },
    {
      key: '/profile',
      label: '个人中心',
      onClick: () => navigate('/profile'),
    },
  ];

  return (
    <AntLayout style={{ minHeight: '100vh' }}>
      <Header style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
        <div style={{ color: 'white', fontSize: '18px', fontWeight: 'bold' }}>
          清洁公司点评平台
        </div>
        <Space>
          <Menu
            theme="dark"
            mode="horizontal"
            items={menuItems}
            style={{ background: 'transparent', border: 'none' }}
          />
          {user ? (
            <Space>
              <span style={{ color: 'white' }}>欢迎，{user.nickname || user.username}</span>
              <Button type="link" onClick={handleLogout} style={{ color: 'white' }}>
                退出
              </Button>
            </Space>
          ) : (
            <Space>
              <Button type="link" onClick={() => navigate('/login')} style={{ color: 'white' }}>
                登录
              </Button>
              <Button type="link" onClick={() => navigate('/register')} style={{ color: 'white' }}>
                注册
              </Button>
            </Space>
          )}
        </Space>
      </Header>
      <Content className="main-content">
        <Outlet />
      </Content>
    </AntLayout>
  );
};

export default Layout; 