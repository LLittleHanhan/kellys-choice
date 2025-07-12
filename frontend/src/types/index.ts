// 用户相关类型
export interface User {
  id: number;
  username: string;
  email: string;
  nickname?: string;
  avatar?: string;
  role: string;
}

// 清洁公司类型
export interface CleaningCompany {
  id: number;
  name: string;
  description?: string;
  address?: string;
  phone?: string;
  serviceArea?: string;
  businessLicense?: string;
  serviceTypes?: string;
  workingHours?: string;
  avgRating: number;
  reviewCount: number;
  status: number;
}

// 评价类型
export interface Review {
  id: number;
  userId: number;
  cleaningCompanyId: number;
  rating: number;
  title?: string;
  content: string;
  images?: string;
  serviceType: string;
  serviceDate: string;
  price?: number;
  likesCount: number;
  status: number;
  createdAt: string;
  user?: User;
  cleaningCompany?: CleaningCompany;
}

// 登录请求类型
export interface LoginRequest {
  username: string;
  password: string;
}

// 注册请求类型
export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
  nickname?: string;
}

// 登录响应类型
export interface LoginResponse {
  token: string;
  type: string;
  id: number;
  username: string;
  email: string;
  nickname?: string;
}

// 评价请求类型
export interface ReviewRequest {
  companyId: number;
  rating: number;
  title?: string;
  content: string;
  images?: string[];
  serviceType: string;
  serviceDate: string;
  price?: number;
}

// API响应类型
export interface ApiResponse<T> {
  data: T;
  message: string;
  success: boolean;
} 