import { NextRequest, NextResponse } from 'next/server';

export async function POST(req: NextRequest) {
  const body = await req.json();
  const idToken = body.idToken;
  const BACKEND_URL = process.env.BACKEND_URL
  try {
    const backendResponse = await fetch(`${BACKEND_URL}/auth/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${idToken}`  
       },
    });
    console.log(backendResponse);
    const data = await backendResponse.json();

    if (!backendResponse.ok) {
      return NextResponse.json(data, { status: backendResponse.status });
    }

    return NextResponse.json(data, { status: 200 });
  } catch (error) {
    console.error('[GOOGLE LOGIN ERROR]', error);
    return NextResponse.json({ error: 'Something went wrong: ' + error }, { status: 500 });
  }
}
