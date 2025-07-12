import React, { useState, useEffect } from 'react';
import { Card, Row, Col, Rate, Input, Select, Space, Typography, Tag } from 'antd';
import { SearchOutlined, PhoneOutlined, EnvironmentOutlined } from '@ant-design/icons';
import { useNavigate } from 'react-router-dom';

const { Search } = Input;
const { Option } = Select;
const { Title, Text } = Typography;

interface CleaningCompany {
  id: number;
  name: string;
  description: string;
  serviceArea: string;
  phone: string;
  address: string;
  avgRating: number;
  reviewCount: number;
  serviceTypes: string[];
  workingHours: string;
}

const Home: React.FC = () => {
  const navigate = useNavigate();
  const [companies, setCompanies] = useState<CleaningCompany[]>([]);
  const [loading, setLoading] = useState(false);
  const [searchKeyword, setSearchKeyword] = useState('');
  const [selectedServiceArea, setSelectedServiceArea] = useState<string>('');
  const [selectedServiceType, setSelectedServiceType] = useState<string>('');

  // 模拟数据
  const mockCompanies: CleaningCompany[] = [
    {
      id: 1,
      name: '洁净家园清洁服务',
      description: '专业提供家庭清洁、办公室清洁、开荒保洁等服务，员工经过专业培训，服务态度好，清洁效果佳。',
      serviceArea: '朝阳区、海淀区、东城区',
      phone: '010-88888888',
      address: '北京市朝阳区建国路88号',
      avgRating: 4.5,
      reviewCount: 128,
      serviceTypes: ['日常保洁', '深度清洁', '开荒保洁', '玻璃清洗'],
      workingHours: '周一至周日 8:00-18:00',
    },
    {
      id: 2,
      name: '绿净清洁公司',
      description: '专注于环保清洁服务，使用环保清洁剂，为您的家庭和办公环境提供健康清洁服务。',
      serviceArea: '海淀区、西城区、丰台区',
      phone: '010-66666666',
      address: '北京市海淀区中关村大街1号',
      avgRating: 4.8,
      reviewCount: 256,
      serviceTypes: ['日常保洁', '深度清洁', '地毯清洗', '空调清洗'],
      workingHours: '周一至周六 7:00-19:00',
    },
    {
      id: 3,
      name: '明亮清洁服务',
      description: '专业团队提供全方位清洁服务，包括家庭清洁、商业清洁、工业清洁等，价格实惠，质量保证。',
      serviceArea: '东城区、西城区、朝阳区',
      phone: '010-77777777',
      address: '北京市东城区王府井大街100号',
      avgRating: 4.2,
      reviewCount: 89,
      serviceTypes: ['日常保洁', '开荒保洁', '外墙清洗', '地板打蜡'],
      workingHours: '周一至周日 6:00-20:00',
    },
  ];

  useEffect(() => {
    setCompanies(mockCompanies);
  }, []);

  const handleSearch = (value: string) => {
    setSearchKeyword(value);
    // 这里应该调用API进行搜索
  };

  const handleServiceAreaChange = (value: string) => {
    setSelectedServiceArea(value);
    // 这里应该根据服务区域筛选
  };

  const handleServiceTypeChange = (value: string) => {
    setSelectedServiceType(value);
    // 这里应该根据服务类型筛选
  };

  const handleCompanyClick = (id: number) => {
    navigate(`/company/${id}`);
  };

  return (
    <div>
      <Title level={2}>本地清洁公司推荐</Title>
      
      <Space direction="vertical" style={{ width: '100%', marginBottom: 24 }}>
        <Search
          placeholder="搜索清洁公司、服务类型、地址..."
          allowClear
          enterButton={<SearchOutlined />}
          size="large"
          onSearch={handleSearch}
        />
        
        <Space>
          <Select
            placeholder="选择服务区域"
            allowClear
            style={{ width: 200 }}
            onChange={handleServiceAreaChange}
          >
            <Option value="朝阳区">朝阳区</Option>
            <Option value="海淀区">海淀区</Option>
            <Option value="东城区">东城区</Option>
            <Option value="西城区">西城区</Option>
            <Option value="丰台区">丰台区</Option>
          </Select>
          
          <Select
            placeholder="选择服务类型"
            allowClear
            style={{ width: 200 }}
            onChange={handleServiceTypeChange}
          >
            <Option value="日常保洁">日常保洁</Option>
            <Option value="深度清洁">深度清洁</Option>
            <Option value="开荒保洁">开荒保洁</Option>
            <Option value="玻璃清洗">玻璃清洗</Option>
            <Option value="地毯清洗">地毯清洗</Option>
            <Option value="空调清洗">空调清洗</Option>
          </Select>
        </Space>
      </Space>

      <Row gutter={[16, 16]}>
        {companies.map((company) => (
          <Col xs={24} sm={12} md={8} lg={6} key={company.id}>
            <Card
              hoverable
              className="business-card"
              onClick={() => handleCompanyClick(company.id)}
            >
              <Title level={4}>{company.name}</Title>
              <Text type="secondary">{company.description}</Text>
              
              <div style={{ marginTop: 8 }}>
                <Rate disabled defaultValue={company.avgRating} />
                <Text style={{ marginLeft: 8 }}>
                  {company.avgRating} ({company.reviewCount}条点评)
                </Text>
              </div>
              
              <div style={{ marginTop: 8 }}>
                <Space direction="vertical" size="small">
                  <Text type="secondary">
                    <EnvironmentOutlined /> {company.serviceArea}
                  </Text>
                  <Text type="secondary">
                    <PhoneOutlined /> {company.phone}
                  </Text>
                  <Text type="secondary">{company.address}</Text>
                </Space>
              </div>
              
              <div style={{ marginTop: 8 }}>
                {company.serviceTypes.map((type) => (
                  <Tag key={type} color="blue" style={{ marginBottom: 4 }}>
                    {type}
                  </Tag>
                ))}
              </div>
              
              <div style={{ marginTop: 8 }}>
                <Text type="secondary">营业时间: {company.workingHours}</Text>
              </div>
            </Card>
          </Col>
        ))}
      </Row>
    </div>
  );
};

export default Home; 