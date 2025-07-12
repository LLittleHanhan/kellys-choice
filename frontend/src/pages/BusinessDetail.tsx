import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Card, Rate, Typography, Divider, List, Avatar, Space, Button, Modal, Form, Input, message, Tag, Descriptions, Select } from 'antd';
import { StarOutlined, UserOutlined, PhoneOutlined, EnvironmentOutlined, ClockCircleOutlined } from '@ant-design/icons';

const { Title, Text, Paragraph } = Typography;
const { TextArea } = Input;

interface Review {
  id: number;
  user: {
    username: string;
    nickname?: string;
  };
  rating: number;
  title?: string;
  content: string;
  serviceType: string;
  serviceDate: string;
  price: number;
  createdAt: string;
}

const CompanyDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [company, setCompany] = useState<any>(null);
  const [reviews, setReviews] = useState<Review[]>([]);
  const [isReviewModalVisible, setIsReviewModalVisible] = useState(false);
  const [reviewForm] = Form.useForm();

  // 模拟数据
  const mockCompany = {
    id: 1,
    name: '洁净家园清洁服务',
    description: '专业提供家庭清洁、办公室清洁、开荒保洁等服务，员工经过专业培训，服务态度好，清洁效果佳。',
    serviceArea: '朝阳区、海淀区、东城区',
    phone: '010-88888888',
    address: '北京市朝阳区建国路88号',
    businessLicense: 'BJ2024001',
    serviceTypes: ['日常保洁', '深度清洁', '开荒保洁', '玻璃清洗'],
    workingHours: '周一至周日 8:00-18:00',
    avgRating: 4.5,
    reviewCount: 128,
  };

  const mockReviews: Review[] = [
    {
      id: 1,
      user: { username: 'user1', nickname: '清洁达人' },
      rating: 5,
      title: '服务很专业',
      content: '洁净家园的服务真的很专业，阿姨们工作认真负责，清洁效果很好，家里焕然一新！',
      serviceType: '日常保洁',
      serviceDate: '2024-01-15',
      price: 200.00,
      createdAt: '2024-01-15',
    },
    {
      id: 2,
      user: { username: 'user2', nickname: '家居爱好者' },
      rating: 4,
      title: '价格合理',
      content: '服务态度不错，清洁效果也很好，价格比较合理，会继续选择这家公司。',
      serviceType: '深度清洁',
      serviceDate: '2024-01-14',
      price: 350.00,
      createdAt: '2024-01-14',
    },
  ];

  useEffect(() => {
    setCompany(mockCompany);
    setReviews(mockReviews);
  }, [id]);

  const handleReviewSubmit = (values: any) => {
    console.log('提交点评:', values);
    message.success('点评提交成功！');
    setIsReviewModalVisible(false);
    reviewForm.resetFields();
  };

  if (!company) {
    return <div>加载中...</div>;
  }

  return (
    <div>
      <Card>
        <Title level={2}>{company.name}</Title>
        <Space align="start">
          <Rate disabled defaultValue={company.avgRating} />
          <Text>{company.avgRating} ({company.reviewCount}条点评)</Text>
        </Space>
        
        <Divider />
        
        <Descriptions title="公司信息" bordered>
          <Descriptions.Item label="公司地址" span={3}>
            <EnvironmentOutlined /> {company.address}
          </Descriptions.Item>
          <Descriptions.Item label="联系电话" span={3}>
            <PhoneOutlined /> {company.phone}
          </Descriptions.Item>
          <Descriptions.Item label="服务区域" span={3}>
            {company.serviceArea}
          </Descriptions.Item>
          <Descriptions.Item label="营业执照" span={3}>
            {company.businessLicense}
          </Descriptions.Item>
          <Descriptions.Item label="营业时间" span={3}>
            <ClockCircleOutlined /> {company.workingHours}
          </Descriptions.Item>
          <Descriptions.Item label="服务类型" span={3}>
            {company.serviceTypes.map((type: string) => (
              <Tag key={type} color="blue" style={{ marginBottom: 4 }}>
                {type}
              </Tag>
            ))}
          </Descriptions.Item>
        </Descriptions>
        
        <Divider />
        
        <Paragraph>{company.description}</Paragraph>
        
        <Divider />
        
        <Space style={{ marginBottom: 16 }}>
          <Title level={4}>用户点评</Title>
          <Button type="primary" onClick={() => setIsReviewModalVisible(true)}>
            写点评
          </Button>
        </Space>
        
        <List
          itemLayout="vertical"
          dataSource={reviews}
          renderItem={(review) => (
            <List.Item>
              <List.Item.Meta
                avatar={<Avatar icon={<UserOutlined />} />}
                title={
                  <Space>
                    <Text>{review.user.nickname || review.user.username}</Text>
                    <Rate disabled defaultValue={review.rating} />
                    <Text type="secondary">{review.createdAt}</Text>
                  </Space>
                }
                description={
                  <Space direction="vertical">
                    <Text strong>{review.title}</Text>
                    <Space>
                      <Tag color="green">{review.serviceType}</Tag>
                      <Text type="secondary">服务日期: {review.serviceDate}</Text>
                      <Text type="secondary">价格: ¥{review.price}</Text>
                    </Space>
                  </Space>
                }
              />
              <Paragraph>{review.content}</Paragraph>
            </List.Item>
          )}
        />
      </Card>

      <Modal
        title="写点评"
        open={isReviewModalVisible}
        onCancel={() => setIsReviewModalVisible(false)}
        footer={null}
      >
        <Form
          form={reviewForm}
          onFinish={handleReviewSubmit}
          layout="vertical"
        >
          <Form.Item
            name="rating"
            label="评分"
            rules={[{ required: true, message: '请选择评分！' }]}
          >
            <Rate />
          </Form.Item>
          
          <Form.Item
            name="title"
            label="标题"
          >
            <Input placeholder="点评标题（可选）" />
          </Form.Item>
          
          <Form.Item
            name="serviceType"
            label="服务类型"
            rules={[{ required: true, message: '请选择服务类型！' }]}
          >
            <Select placeholder="选择服务类型">
              <Option value="日常保洁">日常保洁</Option>
              <Option value="深度清洁">深度清洁</Option>
              <Option value="开荒保洁">开荒保洁</Option>
              <Option value="玻璃清洗">玻璃清洗</Option>
              <Option value="地毯清洗">地毯清洗</Option>
              <Option value="空调清洗">空调清洗</Option>
            </Select>
          </Form.Item>
          
          <Form.Item
            name="serviceDate"
            label="服务日期"
            rules={[{ required: true, message: '请选择服务日期！' }]}
          >
            <Input type="date" />
          </Form.Item>
          
          <Form.Item
            name="price"
            label="服务价格"
            rules={[{ required: true, message: '请输入服务价格！' }]}
          >
            <Input type="number" placeholder="请输入价格" />
          </Form.Item>
          
          <Form.Item
            name="content"
            label="点评内容"
            rules={[{ required: true, message: '请输入点评内容！' }]}
          >
            <TextArea rows={4} placeholder="分享你的清洁体验..." />
          </Form.Item>
          
          <Form.Item>
            <Space>
              <Button type="primary" htmlType="submit">
                提交点评
              </Button>
              <Button onClick={() => setIsReviewModalVisible(false)}>
                取消
              </Button>
            </Space>
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
};

export default CompanyDetail; 