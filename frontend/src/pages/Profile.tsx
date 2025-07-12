import React from 'react';
import { Card, Typography, Avatar, Space, Button, List, Rate, Tag } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import { useSelector } from 'react-redux';
import { RootState } from '../store';

const { Title, Text } = Typography;

const Profile: React.FC = () => {
  const { user } = useSelector((state: RootState) => state.auth);

  // 模拟用户的点评数据
  const mockUserReviews = [
    {
      id: 1,
      companyName: '洁净家园清洁服务',
      rating: 5,
      content: '服务很专业，阿姨们工作认真负责，清洁效果很好',
      serviceType: '日常保洁',
      serviceDate: '2024-01-15',
      price: 200.00,
      createdAt: '2024-01-15',
    },
    {
      id: 2,
      companyName: '绿净清洁公司',
      rating: 4,
      content: '使用环保清洁剂，清洁效果很好，没有刺鼻的味道',
      serviceType: '深度清洁',
      serviceDate: '2024-01-10',
      price: 350.00,
      createdAt: '2024-01-10',
    },
  ];

  if (!user) {
    return <div>请先登录</div>;
  }

  return (
    <div>
      <Card>
        <Space direction="vertical" size="large" style={{ width: '100%' }}>
          <div style={{ textAlign: 'center' }}>
            <Avatar size={64} icon={<UserOutlined />} />
            <Title level={3} style={{ marginTop: 16 }}>
              {user.nickname || user.username}
            </Title>
            <Text type="secondary">{user.email}</Text>
          </div>

          <div>
            <Title level={4}>我的清洁点评</Title>
            <List
              itemLayout="horizontal"
              dataSource={mockUserReviews}
              renderItem={(review) => (
                <List.Item>
                  <List.Item.Meta
                    title={
                      <Space>
                        <Text strong>{review.companyName}</Text>
                        <Rate disabled defaultValue={review.rating} />
                      </Space>
                    }
                    description={
                      <Space direction="vertical">
                        <Text>{review.content}</Text>
                        <Space>
                          <Tag color="green">{review.serviceType}</Tag>
                          <Text type="secondary">服务日期: {review.serviceDate}</Text>
                          <Text type="secondary">价格: ¥{review.price}</Text>
                        </Space>
                        <Text type="secondary">点评时间: {review.createdAt}</Text>
                      </Space>
                    }
                  />
                </List.Item>
              )}
            />
          </div>
        </Space>
      </Card>
    </div>
  );
};

export default Profile; 